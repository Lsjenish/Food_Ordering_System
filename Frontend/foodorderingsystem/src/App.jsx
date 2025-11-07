import './App.css'
import Navbar from './components/Navbar/Navbar'
import { ThemeProvider } from '@emotion/react'
import {darkTheme} from './Theme/DarkTheme'
import CssBaseline from '@mui/material/CssBaseline'
import Home from './components/Home/Home'
import RestaurantDetails from './components/Restaurant/RestaurantDetails'
import Cart from './components/Cart/Cart'
import Profile from './components/Profile/Profile'
import CustomerRouters from './Routers/CustomerRouters'
import { useDispatch, useSelector } from 'react-redux'
import { useEffect } from 'react'
import { getUser } from './components/State/Authentication/Action'
import { findCart } from './components/State/Cart/Action'

function App() {

    const dispatch = useDispatch()
    const {auth} = useSelector(store => store)
    useEffect(() => {
      dispatch(getUser(auth.jwt))
      dispatch(findCart(auth.jwt))
    } , [auth.jwt])

    
  return (
    <>
      <ThemeProvider theme={darkTheme} >
        <CssBaseline />
        <CustomerRouters />
        </ThemeProvider>
    </>
  )
}

export default App
