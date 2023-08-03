const path = require("path");

module.exports = {
  webpack: {
    alias: {
      "@component": path.resolve(__dirname, "src/components"),
      "@config": path.resolve(__dirname, "src/config"),
      "@feature": path.resolve(__dirname, "src/features"),
      "@hook": path.resolve(__dirname, "src/hooks"),
      "@page": path.resolve(__dirname, "src/pages"),
      "@store": path.resolve(__dirname, "src/stores"),
      "@util": path.resolve(__dirname, "src/utils"),
      "@lib": path.resolve(__dirname, "src/lib"),
    },
  },
};
