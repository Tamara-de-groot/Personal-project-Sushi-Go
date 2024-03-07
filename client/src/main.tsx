import React from 'react'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { router } from './router.tsx'
import { SushiGoGameProvider } from '../contexts/SushiGoGameContext.tsx'


import './index.css'


ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <SushiGoGameProvider>
      <RouterProvider router = {router} />
    </SushiGoGameProvider>
  </React.StrictMode>,
)
