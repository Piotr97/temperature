import { TemperatureListComponent } from './components/temperature-list/temperature-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TemperatureCreateComponent } from './components/temperature-create/temperature-create.component';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/list'
    },
  {
    path : 'list',
    component : TemperatureListComponent
  },
  {
    path : 'add',
    component : TemperatureCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
