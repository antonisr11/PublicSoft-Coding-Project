import axios from "axios";
import {ErrorResponse} from "@/entity/ErrorResponse.dto";

interface Response_Success<T> {
    type: "success";
    value: T;
}

interface Response_Error{
    type: "error";
    value: ErrorResponse;
}

type Response<T> = Response_Success<T> | Response_Error;

export async function makeAxiosRequest<T>(url: string): Promise<Response<T>> {
    try {
        const response = await axios.get("http://localhost:8080/" + url);
        return {
            type: "success",
            value: response.data as T
        };
    } catch (err) {
        if (axios.isAxiosError(err) && err.response) {
            return {
                type: "error",
                value: err.response.data as ErrorResponse
            };
        } else {
            return {
                type: "error",
                value: {
                    timestamp: '',
                    status: 500,
                    error: 'Unknown Error',
                    message: 'An unknown error occurred',
                    path: ''
                } as ErrorResponse
            };
        }
    }
}