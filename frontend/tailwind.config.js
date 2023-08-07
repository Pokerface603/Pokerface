module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      backgroundImage: {
        "parchment-box": "url('/src/assets/images/parchment.svg')",
        "wood-button": "url('/src/assets/images/WoodButton.svg')",
        "wood-background": "url('/src/assets/images/WoodBackground.svg')",
        "check-button": "url('/src/assets/images/check.svg')",
        "email-check-ticket":
          "url('/src/assets/images/email-check-ticket.svg')",
      },
      extend: {
        backgroundColor: ["checked"],
        borderColor: ["checked"],
      },
    },
    fontFamily: {
      nexonGothic: ["NexonGothic"],
      colors: {
        ocher: "#C59C70",
        brown_dark: "#482613",
      },
    },
  },
  plugins: [],
};
