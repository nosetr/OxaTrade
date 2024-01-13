/**
 * We are using a dropdown component that is available in Bootstrap
 * to implement our theme switcher.
 * @see https://getbootstrap.com/docs/5.3/customize/color-modes/#javascript
 */
import { useEffect } from "react";
import * as Icon from 'react-bootstrap-icons';
import './ThemeSwitcher.css'

const getStoredTheme = () => localStorage.getItem('theme')
const setStoredTheme = theme => localStorage.setItem('theme', theme)

const getPreferredTheme = () => {
  const storedTheme = getStoredTheme()
  if (storedTheme) {
    return storedTheme
  }

  return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
}

const showActiveTheme = (theme, focus = false) => {
  const themeSwitcher = document.querySelector('#bd-theme')
  if (!themeSwitcher) {
    return
  }

  const themeSwitcherText = document.querySelector('#bd-theme-text')
  const activeThemeIcon = document.querySelector('.theme-icon-active path')
  const btnToActive = document.querySelector(`[data-bs-theme-value="${theme}"]`)
  const svgOfActiveBtn = btnToActive.querySelector('svg path').getAttribute('d')
  document.querySelectorAll('[data-bs-theme-value]').forEach(element => {
    element.classList.remove('active')
    element.setAttribute('aria-pressed', 'false')
  })

  btnToActive.classList.add('active')
  btnToActive.setAttribute('aria-pressed', 'true')
  activeThemeIcon.setAttribute('d', svgOfActiveBtn)
  const themeSwitcherLabel = `${themeSwitcherText.textContent} (${btnToActive.dataset.bsThemeValue})`
  themeSwitcher.setAttribute('aria-label', themeSwitcherLabel)

  if (focus) {
    themeSwitcher.focus()
  }
}

const setTheme = theme => {
  if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
    document.documentElement.setAttribute('data-bs-theme', 'dark')
  } else {
    document.documentElement.setAttribute('data-bs-theme', theme)
  }
}

const Theme = () => {

  useEffect(() => {

    setTheme(getPreferredTheme())

    window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
      const storedTheme = getStoredTheme()
      if (storedTheme !== 'light' && storedTheme !== 'dark') {
        setTheme(getPreferredTheme())
      }
    })

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
  }, []);

  return (
    <div className="d-flex align-items-center dropdown dropup color-modes">
      <button className="btn btn-link px-0 text-decoration-none dropdown-toggle d-flex align-items-center"
        id="bd-theme"
        type="button"
        aria-expanded="false"
        data-bs-toggle="dropdown"
        data-bs-display="static">
        <Icon.CircleHalf className="bi my-1 me-2 theme-icon-active" />
        <span className="d-lg-none ms-2" id="bd-theme-text">Toggle theme</span>
      </button>
      <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme">
        <li>
          <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="light">
            <Icon.SunFill className="bi me-2 opacity-50 theme-icon" />
            Light
            <Icon.Check2 className="bi ms-auto d-none" />
          </button>
        </li>
        <li>
          <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="dark">
            <Icon.MoonFill className="bi me-2 opacity-50 theme-icon" />
            Dark
            <Icon.Check2 className="bi ms-auto d-none" />
          </button>
        </li>
        <li>
          <button type="button" className="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto">
            <Icon.CircleHalf className="bi me-2 opacity-50 theme-icon" />
            Auto
            <Icon.Check2 className="bi ms-auto d-none" />
          </button>
        </li>
      </ul>
    </div>
  );
};

export default Theme;
