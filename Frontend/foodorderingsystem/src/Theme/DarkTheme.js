import { createTheme } from '@mui/material/styles';

export const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#e91e63', // Pink
    },
    secondary: {
      main: '#5A20CB', // Cyan
    },
    black: {
      main: '#0D0D0D', // Black
    },
    background: {
      main : '#0000000', // Black
      default: '#0D0D0D', // Dark background
      paper: '#0D0D0D',   // Dark paper background
    },
    text: {
      primary: '#FFFFFF', // White text
      secondary: '#B0B0B0', // Optional: lighter text
    },
    
  },
});
