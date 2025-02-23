export interface Supplier {
    id: string;
    companyName: string;
    firstName: string;
    lastName: string;
    vat: string;
    irsOffice?: string;
    address?: string;
    zipCode?: number;
    city?: string;
    country?: string;
}