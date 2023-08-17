import React from "react";
import soundEffects from "@config/soundEffects";

function TabItem({ mode, onClick, isSelected }) {
  return (
    <li
      className="flex-grow basis-0 text-center"
      onClick={() => {
        soundEffects.load.play();
        onClick(mode);
      }}
    >
      <div
        className="flex justify-center items-center h-full mr-1 px-7 cursor-pointer"
        style={{
          height: "105px",
          fontFamily: "BouWestern",
          fontSize: "50px",
          color: isSelected ? "white" : "rgba(0, 0, 0, 0.50)",
          backgroundColor: isSelected ? "var(--ocher)" : "var(--brown)",
        }}
      >
        {mode}
      </div>
    </li>
  );
}

export default TabItem;
