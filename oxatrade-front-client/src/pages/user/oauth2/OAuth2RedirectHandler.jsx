import React, { useEffect } from 'react';
import { ACCESS_TOKEN } from '../../../constants';
import { useNavigate, useLocation } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const getUrlParameter = (name) => {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    useEffect(() => {
        const token = getUrlParameter('token');
        const error = getUrlParameter('error');

        if (token) {
            localStorage.setItem(ACCESS_TOKEN, token);
            navigate("/profile", { state: { from: location } });
        } else {
            navigate("/login", { state: { from: location, error: error } });
        }
    }, [navigate, location]);

    return null; // You can return null or any component you like, as this component handles redirects in the useEffect.
};

export default OAuth2RedirectHandler;
