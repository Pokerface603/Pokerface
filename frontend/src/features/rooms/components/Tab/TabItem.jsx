import React from "react";

function TabItem({ mode, onClick }) {
  return (
    <li className="flex-grow basis-0 text-center" onClick={() => onClick(mode)}>
      <div
        className="flex justify-center items-center h-full mr-1 px-7 bg-brown_dark text-black/[0.5] hover:isolate hover:text-white focus:bg-ocher focus:text-white cursor-pointer"
        style={{
          height: "105px",
          fontFamily: "BouWestern",
          fontSize: "50px",
        }}
      >
        {mode}
      </div>
    </li>
  );
}

export default TabItem;
