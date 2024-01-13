import { useState } from 'react';
import {
	createBrowserRouter,
	createRoutesFromElements,
	Route,
	defer
} from "react-router-dom";

import { AuthLayout } from './templates/AuthLayout'
import Layout from './templates/Layout';
import ProtectedLayout from "./templates/ProtectedLayout";
import EmptyLayout from './templates/EmptyLayout';

import HomePage from "./pages/HomePage.jsx";
import ContactPage from "./pages/ContactPage.jsx";
import NoPage from "./pages/NoPage";
import OrdersPage from "./pages/OrdersPage"


import LoginPage from "./pages/user/login/LoginPage";
import RegistrationPage from "./pages/user/registration/RegistrationPage";
import SetAddressPage from './pages/user/address/SetAddressPage'
import DashboardPage from "./pages/user/dashboard/DashboardPage"
import ProfilePage from "./pages/user/profile/ProfilePage"
import OAuth2RedirectHandler from './pages/user/oauth2/OAuth2RedirectHandler'

import { getCurrentUser } from './util/APIUtils'
import LoadingIndicator from './components/LoadingIndicator'

// ideally this would be an API call to server to get logged in user data
const getUserData = () =>
	new Promise((resolve) =>
		setTimeout(() => {
			const user = window.localStorage.getItem("user");
			resolve(user);
		}, 3000)
	);
// for error
// const getUserData = () =>
//   new Promise((resolve, reject) =>
//     setTimeout(() => {
//       reject("Error");
//     }, 3000)
//   );

export const router = createBrowserRouter(
	createRoutesFromElements(
		<Route
			element={<AuthLayout />}
			loader={() => defer({ userPromise: getUserData() })}
		>
			<Route path="/" element={<Layout />}>
				<Route index element={<HomePage />} />
				<Route path="contact" element={<ContactPage />} />
				<Route path="*" element={<NoPage />} />
			</Route>
			<Route element={<EmptyLayout />}>
				<Route path="/login" element={<LoginPage />} />
				<Route path="/signup" element={<RegistrationPage />} />
				<Route path='/add_address' element={<SetAddressPage />} />
			</Route>
			<Route path="/oauth2/redirect" component={<OAuth2RedirectHandler />} />
			<Route path="/my" element={<ProtectedLayout />}>
				<Route index element={<DashboardPage />} />
				<Route path="profile" element={<ProfilePage />} />
				<Route path="orders" element={<OrdersPage />} />
			</Route>
		</Route>
	)
);
