const initialState = {
  cart: {
    content: [],
    cartTotal: 0,
  },
  login: { token: null, user: null },
};

const cartReducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_LOGIN":
      return {
        ...state,
        login: { token: action.payload.token, user: action.payload.user },
      };
    case "LOGOUT":
      return {
        ...state,
        login: { token: null, user: null },
      };
    case "ADD_TO_CART":
      return {
        ...state,
        cart: {
          ...state.cart,
          content: [...state.cart.content, action.payload],
          cartTotal: state.cart.cartTotal + action.payload.price,
        },
      };
    case "REMOVE_FROM_CART": {
      const removedProduct = state.cart.content[action.payload];
      return {
        ...state,
        cart: {
          ...state.cart,
          content: state.cart.content.filter((_, i) => i !== action.payload),
          cartTotal:
            state.cart.cartTotal - (removedProduct ? removedProduct.price : 0),
        },
      };
    }
    default:
      return state;
  }
};

export default cartReducer;
