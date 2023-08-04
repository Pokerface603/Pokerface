const Input = ({ type, onChange, value, placeholder, id, width, height }) => {
  return (
    <input
      type={type}
      onChange={onChange}
      value={value}
      placeholder={placeholder}
      id={id}
      aria-describedby="input box"
      required
      className="bg-transparent block placeholder-black/[0.5] border-solid border-black/[0.6] border-b-2 text-black focus:outline-none focus:bg-gray-50/20 p-2.5 m-2"
      style={{
        width,
        height,
        fontFamily: "Unchained-Spaghetti",
        fontSize: "40px",
      }}
    />
  );
};

export default Input;
