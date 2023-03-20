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

  public sendSelectedGroceries(groceries: Grocery[]): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}/grocery/add`, groceries);
  }

  public getGroceries(): Observable<Grocery[]>{
    return this.http.get<Grocery[]>(`${this.apiServerUrl}/grocery/all`);
  }

  public updateGrocery(grocery: Grocery): Observable<Grocery>{
    return this.http.put<Grocery>(`${this.apiServerUrl}/grocery/update`, grocery);
  }

  public deleteGrocery(groceryCode: string): Observable<any> {
    return this.http.delete<any>(`${this.apiServerUrl}/grocery/${groceryCode}`);
  }

}
