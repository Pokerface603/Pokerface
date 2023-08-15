module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      backgroundImage: {
        "parchment-box": "url('/src/assets/images/parchment.jpg')",
        "wood-button": "url('/src/assets/images/WoodButton.svg')",
        "wood-background": "url('/src/assets/images/wood-background.jpg')",
        "check-button": "url('/src/assets/images/check.svg')",
        "email-check-ticket":
          "url('/src/assets/images/email-check-ticket.svg')",
        room: "url('/src/assets/images/background/Room.svg')",
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
        jack: "#CCC0BB",
        queen: "#846758",
        king: "#664634",
        ace: "#482613",
        joker: "ffd217",
      },
    },
  },
  plugins: [],
};
