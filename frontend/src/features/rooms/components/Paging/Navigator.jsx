import React from "react";
import NavigatorPageButton from "./NavigatorPageButton";

function Navigator({ totalPage, onClickPage }) {
  return (
    <nav aria-label="Page navigation example" className="bg-white">
      <ul className="list-style-none flex">
        {Array.from({ length: totalPage }, (v, i) => i + 1).map((pageNum) => (
          <NavigatorPageButton
            id={pageNum}
            pageNum={pageNum}
            onClick={onClickPage}
          />
        ))}
      </ul>
    </nav>
  );
}

export default Navigator;
