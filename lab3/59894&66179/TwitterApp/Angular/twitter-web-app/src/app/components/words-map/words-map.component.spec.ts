import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WordsMapComponent } from './words-map.component';

describe('WordsMapComponent', () => {
  let component: WordsMapComponent;
  let fixture: ComponentFixture<WordsMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WordsMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WordsMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
