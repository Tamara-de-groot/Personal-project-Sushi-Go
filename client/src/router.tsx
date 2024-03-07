import { createBrowserRouter } from "react-router-dom";
import { RootLayout } from "./layouts/RootLayout.tsx"
import { Play }  from './pages/Play.tsx'
import { SushiGo } from "./pages/SushiGo.tsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <RootLayout />,
        children: [
            {
                index: true,
                element: <SushiGo />
            },

        ]
    }
]);
