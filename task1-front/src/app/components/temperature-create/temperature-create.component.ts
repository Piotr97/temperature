import { Store, Action } from '@ngxs/store';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateTemperatureAction } from '../state/temperature.actions';

@Component({
  selector: 'app-temperature-create',
  templateUrl: './temperature-create.component.html',
  styleUrls: ['./temperature-create.component.sass']
})
export class TemperatureCreateComponent implements OnInit {

  public formGroup : FormGroup;

  constructor(public store : Store, public formBuilder : FormBuilder) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      localization : [null, Validators.required],
      value : [null, Validators.required]
    })
  }

  save(){
    console.log(this.formGroup.value);
    this.store.dispatch(new CreateTemperatureAction({
      localization : this.formGroup.value.localization,
      value : this.formGroup.value.value
    }))
  }

}
