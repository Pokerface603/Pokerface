const path = require("path");

module.exports = {
  webpack: {
    alias: {
      "@component": path.resolve(__dirname, "src/components"),
      "@config": path.resolve(__dirname, "src/config"),
      "@feature": path.resolve(__dirname, "src/features"),
      "@hook": path.resolve(__dirname, "src/hooks"),
      "@route": path.resolve(__dirname, "src/routes"),
      "@store": path.resolve(__dirname, "src/stores"),
      "@util": path.resolve(__dirname, "src/utils"),
      "@lib": path.resolve(__dirname, "src/lib"),
      "@asset": path.resolve(__dirname, "src/assets"),
    },
    module: {
      rules: [
        {
          test: /\.svg$/,
          use: [
            {
              loader: "@svgr/webpack",
              options: {
                native: true,
              },
            },
          ],
        },
      ],
    },
  },
};
