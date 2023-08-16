import React from "react";

const GameResultFooter = ({ result }) => {
  return (
    <div className="flex items-center justify-center mt-10">
      <hr className="w-1/3 border-black border-t-2 mx-4" />
      <div className="flex items-center">
        <span
          className="text-black"
          style={{ fontFamily: "BouWestern", fontSize: "30px" }}>
          Game {result}
        </span>
      </div>
      <hr className="w-1/3 border-black border-t-2 mx-4" />
    </div>
  );
};

export default GameResultFooter;
