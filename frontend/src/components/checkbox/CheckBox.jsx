import React from "react";
import "./style.css";
function CheckBox({ width, height, fontFamily, fontSize }) {
  return (
    <label
      className="flex items-center, select-none"
      style={{
        gap: "9px",
        color: "rgba(0, 0, 0, 0.50)",
      }}
    >
      <input
        className="appearance-none border-solid border-black border-2"
        type="checkbox"
        style={{
          width,
          height,
          borderColor: "#676055",
        }}
      />
      <span
        className={`font-[${fontFamily}]`}
        style={{
          fontSize,
          fontWeight: "bold",
        }}
      >
        비밀방
      </span>
    </label>
  );
}

export default CheckBox;
