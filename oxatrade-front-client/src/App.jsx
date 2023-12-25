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
import LoginPage from "./pages/LoginPage.jsx";
import RegistrationPage from "./pages/RegistrationPage.jsx";
import ContactPage from "./pages/ContactPage.jsx";
import NoPage from "./pages/NoPage";
import SetAddressPage from './pages/SetAddressPage'

import DashboardPage from "./pages/DashboardPage"
import ProfilePage from "./pages/ProfilePage"
import OrdersPage from "./pages/OrdersPage"

import { getCurrentUser } from './util/APIUtils'
import LoadingIndicator from './components/LoadingIndicator'

/**
 * @see https://getbootstrap.com/docs/5.3/customize/color-modes/#javascript
 */
(() => {
	'use strict'

	const getStoredTheme = () => localStorage.getItem('theme')
	const setStoredTheme = theme => localStorage.setItem('theme', theme)

	const getPreferredTheme = () => {
		const storedTheme = getStoredTheme()
		if (storedTheme) {
			return storedTheme
		}

		return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
	}

	const setTheme = theme => {
		console.log('theme ' + theme)
		if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
			document.documentElement.setAttribute('data-bs-theme', 'dark')
		} else {
			document.documentElement.setAttribute('data-bs-theme', theme)
		}
	}

	setTheme(getPreferredTheme())

	const showActiveTheme = (theme, focus = false) => {
		const themeSwitcher = document.querySelector('#bd-theme')
console.log('themeSwitcher ' + themeSwitcher)
		if (!themeSwitcher) {
			return
		}

		const themeSwitcherText = document.querySelector('#bd-theme-text')
		const activeThemeIcon = document.querySelector('.theme-icon-active use')
		const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`)
		const svgOfActiveBtn = btnToActive.querySelector('svg use').getAttribute('href')

		document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
			element.classList.remove('active')
			element.setAttribute('aria-pressed', 'false')
		})

		btnToActive.classList.add('active')
		btnToActive.setAttribute('aria-pressed', 'true')
		activeThemeIcon.setAttribute('href', svgOfActiveBtn)
		const themeSwitcherLabel = `${themeSwitcherText.textContent} (${btnToActive.dataset.bsThemeValue})`
		themeSwitcher.setAttribute('aria-label', themeSwitcherLabel)

		if (focus) {
			themeSwitcher.focus()
		}
	}

	window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
		const storedTheme = getStoredTheme()
		if (storedTheme !== 'light' && storedTheme !== 'dark') {
			setTheme(getPreferredTheme())
		}
	})

	window.addEventListener('DOMContentLoaded', () => {
		showActiveTheme(getPreferredTheme())

		document.querySelectorAll('[data-bs-theme-value]')
			.forEach(toggle => {
				toggle.addEventListener('click', () => {
					const theme = toggle.getAttribute('data-bs-theme-value')
					setStoredTheme(theme)
					setTheme(theme)
					showActiveTheme(theme, true)
				})
			})
	})
})()

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

			<Route path="/my" element={<ProtectedLayout />}>
				<Route index element={<DashboardPage />} />
				<Route path="profile" element={<ProfilePage />} />
				<Route path="orders" element={<OrdersPage />} />
			</Route>
		</Route>
	)
);
