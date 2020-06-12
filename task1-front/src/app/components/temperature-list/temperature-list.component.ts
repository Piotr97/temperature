import { ListTemperatureAction } from './../state/temperature.actions';
import { Store, Select } from '@ngxs/store';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Temperature } from 'src/api/models';

@Component({
  selector: 'app-temperature-list',
  templateUrl: './temperature-list.component.html',
  styleUrls: ['./temperature-list.component.sass']
})
export class TemperatureListComponent implements OnInit {

  @Select(state => state.temperature.temperatureList)
  temperatureList$: Observable<Temperature[]>

  displayedColumns: string[] = ['localization', 'value'];

  constructor(public store: Store) { }

  ngOnInit(): void {
    this.store.dispatch(new ListTemperatureAction());
  }



}
