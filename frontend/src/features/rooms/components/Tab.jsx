import React from "react";

const Tab = () => {
  return (
    <>
      {/* Tab은 수정이 필요함. 뭐가 연결된건지 전혀 모르겠음. */}
      <ul
        className="flex list-none flex-row flex-wrap pl-0"
        role="tablist"
        data-te-nav-ref
      >
        <li role="presentation" className="flex-grow basis-0 text-center">
          <div
            className="flex justify-center items-center h-full mr-1 px-7 bg-brown_dark text-black/[0.5] hover:isolate hover:text-white focus:bg-ocher focus:text-white"
            data-te-toggle="pill"
            data-te-target="#tabs-home02"
            data-te-nav-active
            role="tab"
            aria-controls="tabs-home02"
            aria-selected="true"
            tabIndex="0"
            style={{
              height: "105px",
              fontFamily: "BouWestern",
              fontSize: "50px",
            }}
          >
            NORMAL
          </div>
        </li>
        <li role="presentation" className="flex-grow basis-0 text-center">
          <div
            className="flex justify-center items-center h-full mr-1 px-7 bg-brown_dark text-black/[0.5] hover:isolate hover:text-white focus:bg-ocher  focus:text-white"
            data-te-toggle="pill"
            data-te-target="#tabs-home02"
            data-te-nav-active
            role="tab"
            aria-controls="tabs-home02"
            aria-selected="true"
            style={{
              height: "105px",
              fontFamily: "BouWestern",
              fontSize: "50px",
            }}
            tabIndex="0"
          >
            BLIND
          </div>
        </li>
        <li role="presentation" className="flex-grow basis-0 text-center">
          <div
            className="flex justify-center items-center h-full px-7 bg-brown_dark text-black/[0.5] hover:isolate hover:text-white focus:bg-ocher  focus:text-white"
            data-te-toggle="pill"
            data-te-target="#tabs-home02"
            data-te-nav-active
            role="tab"
            aria-controls="tabs-home02"
            aria-selected="true"
            style={{
              height: "105px",
              fontFamily: "BouWestern",
              fontSize: "50px",
            }}
            tabIndex="0"
          >
            EMOTION
          </div>
        </li>
      </ul>
    </>
  );
};

export default Tab;
