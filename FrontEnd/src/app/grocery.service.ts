import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grocery } from './grocery';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class GroceryService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getGroceries(): Observable<Grocery[]>{
    return this.http.get<Grocery[]>(`${this.apiServerUrl}/grocery/all`);
  }

  public addGrocery(grocery: Grocery): Observable<Grocery>{
    return this.http.post<Grocery>(`${this.apiServerUrl}/grocery/add`,grocery);
  }

  public updateGrocery(grocery: Grocery): Observable<Grocery>{
    return this.http.put<Grocery>(`${this.apiServerUrl}/grocery/update`, grocery);
  }

  public deleteGrocery(groceryId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/grocery/delete/${groceryId}`);
  }

  

  

}
