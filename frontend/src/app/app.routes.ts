import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';
import { LessonDetailsComponent } from './lesson-details/lesson-details';
import { SubjectDetailsComponent } from './subject-details/subject-details';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'subjects/:id', component: SubjectDetailsComponent },
  { path: 'lessons/:id', component: LessonDetailsComponent },
  { path: '**', redirectTo: '' },
];
