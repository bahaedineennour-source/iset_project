import { Component, Input, OnChanges, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Chapter, Lesson } from '../models/course.models';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-chapter',
  imports: [RouterLink],
  templateUrl: './chapter.html',
  styleUrl: './chapter.css',
})
export class ChapterComponent implements OnChanges {
  private readonly courseService = inject(CourseService);

  @Input({ required: true }) chapter!: Chapter;
  @Input() open = false;

  protected readonly lessons = signal<Lesson[]>([]);
  protected readonly loading = signal(false);
  protected readonly error = signal<string | null>(null);
  protected readonly loaded = signal(false);

  ngOnChanges(): void {
    if (this.open && !this.loaded() && !this.loading()) {
      this.loadLessons();
    }
  }

  protected toggle(): void {
    this.open = !this.open;
    if (this.open && !this.loaded()) {
      this.loadLessons();
    }
  }

  private loadLessons(): void {
    this.loading.set(true);
    this.error.set(null);
    this.courseService.getLessons(this.chapter.id).subscribe({
      next: (lessons) => {
        this.lessons.set(lessons);
        this.loaded.set(true);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Impossible de charger les cours de ce chapitre.');
        this.loading.set(false);
      },
    });
  }
}
