import { Component, ElementRef, OnInit  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Grocery } from './grocery';
import { GroceryService } from './grocery.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  selectedGroceries: Grocery[] = [];
  appTitle= "FrontEnd";


  // title(title: any) {
  //   title="FrontEnd";
  //   throw new Error('Method not implemented.');
  // }

  public groceries: Grocery[] | undefined | null;
  public editGrocery:Grocery | undefined | null;
  public deleteGrocery:Grocery | undefined | null;
  

  constructor(private groceryService: GroceryService, private elRef: ElementRef){}

  ngOnInit(): void {
      this.loadGroceries();
      this.getGroceries();
      this.checkBox();
      this.deleteRows();
      this.onStartButtonClick();
  }

  onProductCheckboxChange(event: Event, product: Grocery) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.selectedGroceries.push(product);
    } else {
      const index = this.selectedGroceries.findIndex(item => item.groceryCode === product.groceryCode);
      if (index !== -1) {
        this.selectedGroceries.splice(index, 1);
      }
    }
  }

  public onStartButtonClick(): void {
    const groceriesToSend: Grocery[] = [];
    const tableRows = this.elRef.nativeElement.querySelectorAll('tbody tr') as NodeListOf<HTMLTableRowElement>;
  
    tableRows.forEach((row: HTMLTableRowElement) => {
      const checkbox = row.cells[0].querySelector('input[type="checkbox"]') as HTMLInputElement;
  
      // work with only checked boxes
      if (checkbox.checked) {
        const grocery: Grocery = {
          groceryCode: checkbox.id,
          name: row.cells[1].textContent?.trim() || '',
          quantity: row.cells[2].textContent?.trim() || ''
        };
  
        //If a product does not have a groceryCode, it means it is a new product that has not been saved to the database
        if (!grocery.groceryCode) {
          groceriesToSend.push(grocery);
        }
      }
    });
  
    console.log('Groceries to send:', groceriesToSend);
  
  
    // send data to the backend
    this.groceryService.sendSelectedGroceries(groceriesToSend).subscribe((response) => {
      console.log('Selected groceries sent to backend:', response);
      this.selectedGroceries = [];

      // Deselecting checkboxes in a table
      const checkboxes = document.querySelectorAll('input[type=checkbox]');
      checkboxes.forEach(checkbox => {
        (checkbox as HTMLInputElement).checked = false;
      });
    
    }, (error) => {
      console.error('Error sending selected groceries to backend:', error);
    });
  }
  // load data from server and refresh table
  loadGroceries(): void {
    this.groceryService.getGroceries().subscribe((response) => {
      this.groceries = response;
    });
  }

  public deleteRows(): void {
    const deleteButton = document.getElementById('deleteButton') as HTMLButtonElement;
  
    deleteButton.addEventListener('click', () => {
      const checkboxes = this.elRef.nativeElement.querySelectorAll('tbody input[type="checkbox"]:checked') as NodeListOf<HTMLInputElement>;
  
      checkboxes.forEach((checkbox) => {
        const row = checkbox.closest('tr');
        if (row) {
          const groceryCode = (row.querySelector('input[type="checkbox"]') as HTMLInputElement).id;
  
          this.groceryService.deleteGrocery(groceryCode).subscribe((response) => {
            console.log('Grocery with id', groceryCode, 'deleted:', response);
            this.loadGroceries();
          });
  
          row.remove();
        }
      });
    });
  }

// u

  public getGroceries():void{
    this.groceryService.getGroceries().subscribe(
      (Response: Grocery[])=>{
        this.groceries = Response;
        console.log(this.groceries);
      }
    )
  };

  //add button (with creating the row)
  
  public addRow(): void {
    const tbody = document.querySelector('tbody') as HTMLTableSectionElement;
  
    const row = tbody.insertRow();
    row.classList.add('td', 'table', 'td:last-child');
  
    const checkboxCell = row.insertCell();
    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkboxCell.appendChild(checkbox);
  
    const groceryNameCell = row.insertCell();
    const groceryName = document.createElement('td:last-child');
    groceryName.contentEditable = 'true';
    groceryNameCell.appendChild(groceryName);
  
    const quantityCell = row.insertCell();
    const quantity = document.createElement('td:last-child');
    quantity.contentEditable = 'true';
    quantityCell.appendChild(quantity);
  
    const rimiPriceCell = row.insertCell();
    const rimiPrice = document.createElement('td:last-child');
    rimiPriceCell.appendChild(rimiPrice);
  
    const prismaPriceCell = row.insertCell();
    const prismaPrice = document.createElement('td:last-child');
    prismaPriceCell.appendChild(prismaPrice);
  
    const barboraPriceCell = row.insertCell();
    const barboraPrice = document.createElement('td:last-child');
    barboraPriceCell.appendChild(barboraPrice);
  }


  public checkBox(): void {
    const selectAllCheckbox = document.getElementById('selectAll') as HTMLInputElement;

    selectAllCheckbox.addEventListener('click', () => {
      const checkboxes = this.elRef.nativeElement.querySelectorAll('tbody input[type="checkbox"]') as NodeListOf<HTMLInputElement>;

      for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
      }
    });
  }
}