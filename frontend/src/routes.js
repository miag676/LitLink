import Home from './pages/Home';
import BookDetails from './pages/BookDetails';
import Profile from './pages/Profile';
import Register from "./pages/Register";

const routes = [
  { path: '/', component: Home },
  { path: '/book/:id', component: BookDetails },
  { path: '/profile', component: Profile },
  { path: "/register", component: Register },
];

export default routes;
