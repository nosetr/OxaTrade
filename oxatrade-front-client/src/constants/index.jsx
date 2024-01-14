export const API_BASE_URL = 'http://localhost:8080';

export const API_AUTH_URL = 'http://localhost:8083';
export const ACCESS_TOKEN = 'accessToken';
export const OAUTH2_REDIRECT_URI = 'http://localhost:5173/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_AUTH_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_AUTH_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_AUTH_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const TITLES = [
    { value: 'f', label: 'Ms.' },
    { value: 'm', label: 'Mr.' },
    { value: 'Dr.(f)', label: 'Dr. (f)' },
    { value: 'Dr.(m)', label: 'Dr. (m)' },
    { value: 'Prof.(f)', label: 'Prof. (f)' },
    { value: 'Prof.(m)', label: 'Prof. (m)' },
    { value: null, label: 'No title' }
]