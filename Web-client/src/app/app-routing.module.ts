import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GraphsComponent} from './view/graphs/graphs.component';
import {DashboardComponent} from './view/dashboard/dashboard.component';


const routes: Routes = [
  { path: 'graph',
    component: GraphsComponent,
  },
  { path: 'dashboard',
    component: DashboardComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
