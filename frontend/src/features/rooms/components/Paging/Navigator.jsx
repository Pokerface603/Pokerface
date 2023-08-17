import React from "react";
import NavigatorPageButton from "./NavigatorPageButton";

function Navigator({ totalPage, onClickPage }) {
  return (
    <nav
      aria-label="Page navigation example"
      className="bg-amber-800"
      style={{ fontFamily: "NexonGothic", fontSize: "20px" }}
    >
      <ul class="list-style-none flex">
        {Array.from({ length: totalPage }, (v, i) => i + 1).map((pageNum) => (
          <NavigatorPageButton
            key={pageNum}
            pageNum={pageNum}
            onClick={onClickPage}
          />
        ))}
      </ul>
    </nav>
  );
}

export default Navigator;
