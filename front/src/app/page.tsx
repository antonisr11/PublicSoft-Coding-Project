'use client';

import {useEffect, useState} from 'react';
import {Supplier} from "@/entity/Supplier.dto";
import {ErrorResponse} from "@/entity/ErrorResponse.dto";
import {makeAxiosRequest} from "@/components/generalTools";

const SuppliersPage = () => {
    const [suppliers, setSuppliers] = useState<Supplier[]>([]);
    const [searchCompanyName, setSearchCompanyName] = useState('');
    const [searchVAT, setSearchVAT] = useState('');
    const [isDataClearOfSearch, setIsDataClearOfSearch] = useState(true);
    const [error, setError] = useState<ErrorResponse | undefined>();

    useEffect(() => {
        // Fetch all suppliers when the page loads
        fetchAndSaveData();
    }, []);

    /**
     * Fetches all suppliers and saves them to the state
     */
    function fetchAndSaveData() {
        setSearchCompanyName('');
        setSearchVAT('');
        makeAxiosRequest<Supplier[]>('api/suppliers/find/all').then((response) => {
            if (response.type === "success") {
                setSuppliers(response.value);
                setError(undefined);
            } else {
                setSuppliers([]);
                setError(response.value);
            }
        }).finally(() => {
            setIsDataClearOfSearch(true);
        });
    }

    /**
     * Fetches suppliers by company name and saves them to the state
     */
    function handleSearchByCompanyName() {
        if (!searchCompanyName) return; // Do not search if the input is empty
        setSearchVAT('');
        makeAxiosRequest<Supplier[]>('api/suppliers/find/companyName/' + searchCompanyName).then((response) => {
            if (response.type === "success") {
                setSuppliers(response.value);
                setError(undefined);
                setIsDataClearOfSearch(false);
            } else {
                setSuppliers([]);
                setError(response.value);
            }
        }).finally(() => {
            setIsDataClearOfSearch(false);
        });
    }

    /**
     * Fetches suppliers by VAT and saves them to the state
     */
    function handleSearchByVAT() {
        if (!searchVAT) return; // Do not search if the input is empty
        setSearchCompanyName('');
        makeAxiosRequest<Supplier>('api/suppliers/find/vat/' + searchVAT).then((response) => {
            if (response.type === "success") {
                setSuppliers([response.value]);
                setError(undefined);
            } else {
                setSuppliers([]);
                setError(response.value);
            }
        }).finally(() => {
            setIsDataClearOfSearch(false);
        });
    }

    return (
        <div className="container mt-5">
            <h1
                className="mb-4 text-center"
                onClick={() => window.open('https://www.youtube.com/watch?v=dQw4w9WgXcQ', '_blank')}
            >
                Supplier(s)
            </h1>

            <div className="row mb-4">
                <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search by Company Name"
                        value={searchCompanyName}
                        onChange={(e) => setSearchCompanyName(e.target.value)}
                    />
                    <button className="btn btn-primary w-100 mt-2" onClick={handleSearchByCompanyName}>
                        Search by Company Name
                    </button>
                </div>
                <div className="col-md-6">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search by VAT"
                        value={searchVAT}
                        onChange={(e) => setSearchVAT(e.target.value)}
                    />
                    <button className="btn btn-primary w-100 mt-2" onClick={handleSearchByVAT}>
                        Search by VAT
                    </button>
                </div>
            </div>

            {!isDataClearOfSearch && (
                <div className="row mb-4">
                    <div className="col-md-12">
                        <button className="btn btn-warning w-100" onClick={fetchAndSaveData}>
                            Clear Search
                        </button>
                    </div>
                </div>
            )}

            {error && (
                <div className="alert alert-danger" role="alert">
                    <strong>Error:</strong> {error.message}
                </div>
            )}

            <div className="table-responsive">
                <table className="table table-striped table-bordered">
                    <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Company Name</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>VAT</th>
                        <th>IRS Office</th>
                        <th>Address</th>
                        <th>Zip Code</th>
                        <th>City</th>
                        <th>Country</th>
                    </tr>
                    </thead>
                    <tbody>
                    {suppliers.map((supplier) => (
                        <tr key={supplier.id}>
                            <td>{supplier.id}</td>
                            <td>{supplier.companyName}</td>
                            <td>{supplier.firstName}</td>
                            <td>{supplier.lastName}</td>
                            <td>{supplier.vat}</td>
                            <td>{supplier.irsOffice}</td>
                            <td>{supplier.address}</td>
                            <td>{supplier.zipCode}</td>
                            <td>{supplier.city}</td>
                            <td>{supplier.country}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default SuppliersPage;
