import React from "react";

function NavigatorPageButton({ pageNum, onClick }) {
  return (
    <li onClick={() => onClick(pageNum)}>
      <button className="relative block rounded bg-transparent px-3 py-1.5 text-sm text-white transition-all duration-300 hover:bg-amber-700  dark:text-white dark:hover:bg-neutral-700 dark:hover:text-white">
        {pageNum}
      </button>
    </li>
  );
}

export default NavigatorPageButton;
