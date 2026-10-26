import { Component, OnInit, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Subject } from '../models/course.models';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent implements OnInit {
  private readonly courseService = inject(CourseService);

  protected readonly subjects = signal<Subject[]>([]);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);

  ngOnInit(): void {
    this.courseService.getSubjects().subscribe({
      next: (subjects) => {
        this.subjects.set(subjects);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Impossible de charger les matières. Vérifiez que le serveur Spring Boot est démarré.');
        this.loading.set(false);
      },
    });
  }
}
