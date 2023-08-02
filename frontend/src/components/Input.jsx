const Input = ({ width, height, type, placeholder }) => {
  return (
      <input
        type={`${type}`}
        id="input-box"
        aria-describedby="input box"
        className="bg-none placeholder-black/[0.5] border-solid border-black/[0.6] border-b-2 text-black focus:outline-none focus:bg-gray-50 block p-2.5 m-2"
        style={{
          width,
          height,
          fontFamily: "Unchained-Spaghetti",
          fontSize: "45px",
        }}
        placeholder={`${placeholder}`}
      />
  );
};

export default Input;
