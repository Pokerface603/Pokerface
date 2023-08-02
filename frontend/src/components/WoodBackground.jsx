import React from "react";

const WoodBackground = ({ children }) => {
  return (
    <div className="bg-wood-background bg-cover w-screen h-screen flex justify-center items-center">
      {children}
    </div>
  );
};

export default WoodBackground;
