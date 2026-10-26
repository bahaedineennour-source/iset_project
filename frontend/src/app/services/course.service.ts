import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Chapter, Lesson, LessonDetails, Subject } from '../models/course.models';

@Injectable({ providedIn: 'root' })
export class CourseService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = '/api';

  getSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.apiUrl}/subjects`);
  }

  getSubject(id: number): Observable<Subject> {
    return this.http.get<Subject>(`${this.apiUrl}/subjects/${id}`);
  }

  getChapters(subjectId: number): Observable<Chapter[]> {
    return this.http.get<Chapter[]>(`${this.apiUrl}/subjects/${subjectId}/chapters`);
  }

  getLessons(chapterId: number): Observable<Lesson[]> {
    return this.http.get<Lesson[]>(`${this.apiUrl}/chapters/${chapterId}/lessons`);
  }

  getLesson(id: number): Observable<LessonDetails> {
    return this.http.get<LessonDetails>(`${this.apiUrl}/lessons/${id}`);
  }
}
