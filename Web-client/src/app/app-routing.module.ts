import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {GraphsComponent} from './view/graphs/graphs.component';
import {DashboardComponent} from './view/dashboard/dashboard.component';
import {SensorsGroupComponent} from './view/settings/sensors-group.component';
import {HistoryComponent} from './view/history/history.component';
import {IncedentsComponent} from './view/incedents/incedents.component';
import {SensorsComponent} from './view/settings/sensors/sensors.component';


const routes: Routes = [
  { path: 'graph',
    component: GraphsComponent,
  },
  { path: 'sensors-groups',
    component: SensorsGroupComponent,
  },
  { path: 'sensors/:id',
    component: SensorsComponent,
  },
  { path: 'history',
    component: HistoryComponent,
  },
  { path: 'incidents',
    component: IncedentsComponent,
  },
  { path: '**',
    component: DashboardComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
