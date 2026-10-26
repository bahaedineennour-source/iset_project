import { Component, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { LessonDetails } from '../models/course.models';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-lesson-details',
  imports: [RouterLink],
  templateUrl: './lesson-details.html',
  styleUrl: './lesson-details.css',
})
export class LessonDetailsComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly courseService = inject(CourseService);

  protected readonly lesson = signal<LessonDetails | null>(null);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (Number.isNaN(id)) {
      this.error.set('Identifiant de cours invalide.');
      this.loading.set(false);
      return;
    }

    this.courseService.getLesson(id).subscribe({
      next: (lesson) => {
        this.lesson.set(lesson);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Impossible de charger ce cours. Il est peut-être introuvable.');
        this.loading.set(false);
      },
    });
  }
}
