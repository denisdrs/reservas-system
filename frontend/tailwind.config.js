/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: 'var(--primary-color)',
        secondary: 'var(--secondary-color)',
        'text-color': 'var(--text-color)',
        'background-color': 'var(--background-color)',
        white: 'var(--white)',
      },
    },
  },
  plugins: [],
}