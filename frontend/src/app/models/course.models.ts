export interface Subject {
  id: number;
  name: string;
  description: string;
}

export interface Chapter {
  id: number;
  title: string;
  chapterNumber: number;
  subjectId: number;
}

export interface Lesson {
  id: number;
  title: string;
  lessonNumber: number;
}

export interface LessonDetails {
  id: number;
  title: string;
  content: string;
  lessonNumber: number;
  attachmentUrl: string | null;
  chapterId: number;
  chapterTitle: string;
  subjectId: number;
  subjectName: string;
}
