module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      backgroundImage: {
        "parchment-box": "url('/src/assets/images/parchment.svg')",
        "wood-button": "url('/src/assets/images/WoodButton.svg')",
        "wood-background": "url('/src/assets/images/WoodBackground.svg')",
      },
      colors: {
        ocher: "#C59C70",
        brown_dark: "#482613",
      }
    },
  },
  plugins: [],
};
