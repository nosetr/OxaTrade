import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { Bounce, ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import './global.css'

import { router } from './App'

const CloseButton = ({ closeToast }) => (
  <button
    class="btn-close btn-close-white me-2 m-auto btn btn-secondary"
    aria-label="Close"
    onClick={closeToast}></button>
);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ToastContainer
      closeButton={CloseButton}
      transition={Bounce} />
    <RouterProvider router={router} />
  </React.StrictMode>,
)
