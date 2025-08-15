import './App.css'
import Navbar from './components/Navbar/Navbar'
import { ThemeProvider } from '@emotion/react'
import {darkTheme} from './Theme/DarkTheme'
import CssBaseline from '@mui/material/CssBaseline'
import Home from './components/Home/Home'
import RestaurantDetails from './components/Restaurant/RestaurantDetails'
import Cart from './components/Cart/Cart'
import Profile from './components/Profile/Profile'
function App() {

  return (
    <>
      <ThemeProvider theme={darkTheme} >
        <CssBaseline />
        <Navbar />
        
        <Profile />
        {/* <Cart /> */}
         {/* <RestaurantDetails /> */}
       
        {/* <Home /> */}
        
        
        </ThemeProvider>
    </>
  )
}

export default App
