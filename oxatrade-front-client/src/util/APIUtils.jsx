/**
 * In this conversion:
 * 1. I replaced the fetch API with the axios library, which simplifies the HTTP request
 *    and response handling.
 * 2. I changed the request function to use async/await syntax for cleaner asynchronous code.
 * 3. The functions getCurrentUser, login, and signup now use the axios library to make the
 *    HTTP requests, handling both successful and error responses more consistently.
 */

import axios from 'axios';
import { API_BASE_URL, ACCESS_TOKEN } from '../constants';

const request = async (options) => {
    const headers = {
        'Content-Type': 'application/json',
    };

    if (localStorage.getItem(ACCESS_TOKEN)) {
        headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
    }

    const defaults = { headers };
    options = Object.assign({}, defaults, options);

    try {
        const response = await axios(options);
        return response.data;
    } catch (error) {
        return Promise.reject(error.response ? error.response.data : error.message);
    }
};

export const getCurrentUser = () => {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET',
    });
};

export const login = (loginRequest) => {
    return request({
        url: API_BASE_URL + "/auth/login",
        method: 'POST',
        data: JSON.stringify(loginRequest),
    });
};

export const signup = (signupRequest) => {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        data: JSON.stringify(signupRequest),
    });
};
