package se.vgregion.portal.favorites.favoritesquickadd.controller;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import se.vgregion.portal.favorites.domain.jpa.Favorite;
import se.vgregion.portal.favorites.exception.CreateFavoriteException;
import se.vgregion.portal.favorites.service.FavoriteService;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Controller class for the view mode in User Favorites Quick Add portlet.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Controller
@RequestMapping(value = "VIEW")
public class FavoritesQuickAddViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavoritesQuickAddViewController.class.getName());
    
    private FavoriteService favoriteService;

    /**
     * Constructor.
     *
     */
    @Autowired
    public FavoritesQuickAddViewController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }    

    /**
     * The default render method - provides user favorites to the view
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return the view
     */
    @RenderMapping()
    public String showQuickAdd(RenderRequest request, RenderResponse response, final ModelMap model) {
    	
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long scopeGroupId = themeDisplay.getScopeGroupId();
        long companyId = themeDisplay.getCompanyId();
        long userId = themeDisplay.getUserId();
        long currentPlid = themeDisplay.getLayout().getPlid();
        boolean isSignedIn = themeDisplay.isSignedIn();
        
        boolean isCurrentLayoutUserFavorite = favoriteService.isLayoutUserFavorite(companyId, scopeGroupId, userId, currentPlid);
        List<Layout> favoriteLayouts = favoriteService.getUserFavoriteLayouts(companyId, scopeGroupId, userId);
        
        model.addAttribute("currentPlid", currentPlid);
        model.addAttribute("favoriteLayouts", favoriteLayouts);
        model.addAttribute("isCurrentLayoutUserFavorite", isCurrentLayoutUserFavorite);
        model.addAttribute("isSignedIn", isSignedIn);

        return "view";
    }
    
    /**
     * Method handling Action request for adding a favorite
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping(params = "action=addFavorite")
    public final void addFavorite(ActionRequest request, ActionResponse response, final ModelMap model) {
    	
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long groupId = themeDisplay.getScopeGroupId();
        long userId = themeDisplay.getUserId();
        
        long favoritePlid = ParamUtil.getLong(request, "favoritePlid", 0);
        
        if (favoritePlid != 0) {
            Favorite favorite = new Favorite(companyId, groupId, userId, favoritePlid);
            
            try {
    			favoriteService.addFavorite(favorite);
    		} catch (CreateFavoriteException e) {
    			LOGGER.error(e.getMessage(), e);
    		}
        }
        
        response.setRenderParameter("showView", "");
    }
    
    /**
     * Method handling Action request for deleting a favorite
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping(params = "action=deleteFavorite")
    public final void deleteFavorite(ActionRequest request, ActionResponse response, final ModelMap model) {
    	
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long groupId = themeDisplay.getScopeGroupId();
        long userId = themeDisplay.getUserId();
    	
        long favoritePlid = ParamUtil.getLong(request, "favoritePlid", 0);
        
        Favorite favorite = favoriteService.findUserFavoriteByPlid(companyId, groupId, userId, favoritePlid);
        
        if (favorite != null) {
        	favoriteService.deleteFavorite(favorite.getId());
        }
        
        response.setRenderParameter("showView", "");
    }

}

