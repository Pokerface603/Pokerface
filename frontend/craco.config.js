const path = require("path");

module.exports = {
  webpack: {
    alias: {
      "@component": path.resolve(__dirname, "src/components"),
      "@config": path.resolve(__dirname, "src/components"),
      "@feature": path.resolve(__dirname, "src/components"),
      "@hook": path.resolve(__dirname, "src/components"),
      "@page": path.resolve(__dirname, "src/components"),
      "@store": path.resolve(__dirname, "src/components"),
      "@util": path.resolve(__dirname, "src/components"),
    },
  },
};
