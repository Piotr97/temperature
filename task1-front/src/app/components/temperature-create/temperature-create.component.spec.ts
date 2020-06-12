import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemperatureCreateComponent } from './temperature-create.component';

describe('TemperatureCreateComponent', () => {
  let component: TemperatureCreateComponent;
  let fixture: ComponentFixture<TemperatureCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemperatureCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemperatureCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
