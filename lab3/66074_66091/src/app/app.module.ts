import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent , CommentComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TagCloudModule} from 'angular-tag-cloud-module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatInputModule} from '@angular/material/input';
import {DemoMaterialModule} from './material-module';
import {MatNativeDateModule} from '@angular/material/core';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
@NgModule({
  imports: [
    TagCloudModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatExpansionModule,
    MatInputModule,
    DemoMaterialModule,
    MatNativeDateModule,
    ReactiveFormsModule,
  ],
  entryComponents: [AppComponent, CommentComponent],
  declarations: [AppComponent, CommentComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
