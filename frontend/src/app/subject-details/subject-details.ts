import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ChapterComponent } from '../chapter/chapter';
import { Chapter, Subject } from '../models/course.models';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-subject-details',
  imports: [RouterLink, ChapterComponent],
  templateUrl: './subject-details.html',
  styleUrl: './subject-details.css',
})
export class SubjectDetailsComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly courseService = inject(CourseService);

  protected readonly subject = signal<Subject | null>(null);
  protected readonly chapters = signal<Chapter[]>([]);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (Number.isNaN(id)) {
      this.error.set('Identifiant de matière invalide.');
      this.loading.set(false);
      return;
    }

    forkJoin({
      subject: this.courseService.getSubject(id),
      chapters: this.courseService.getChapters(id),
    }).subscribe({
      next: ({ subject, chapters }) => {
        this.subject.set(subject);
        this.chapters.set(chapters);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Impossible de charger cette matière. Elle est peut-être introuvable.');
        this.loading.set(false);
      },
    });
  }
}
