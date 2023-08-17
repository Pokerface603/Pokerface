import React from "react";
import Magnifier from "../../../assets/images/rooms/magnifier.svg";

const SearchBar = ({ onClickSearch, onInputSearchKeyword, searchKeyword }) => {
  return (
    <div>
      <label
        htmlFor="default-search"
        className="mb-2 text-sm font-medium text-gray-900 sr-only"
      >
        Search
      </label>
      <div className="relative">
        <div className="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
          <img src={Magnifier} alt="Magnifier" />
        </div>
        <input
          type="search"
          id="default-search"
          className="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-wood-button bg-cover focus:ring-blue-500 focus:border-blue-500"
          style={{fontFamily:"NexonGothic", fontSize:"20px"}}
          placeholder="검색어를 입력하세요"
          required
          onChange={onInputSearchKeyword}
          value={searchKeyword}
        />
        <button
          type="submit"
          className="text-white absolute right-2.5 bottom-2.5 bg-amber-900 hover:bg-amber-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2"
          onClick={onClickSearch}
        >
          Search
        </button>
      </div>
    </div>
  );
};

export default SearchBar;
