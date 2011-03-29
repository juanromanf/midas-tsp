package com.midas.tsp.controller.impl;

import java.util.List;
import java.util.Properties;

import com.midas.tsp.annotations.Loc;
import com.midas.tsp.annotations.LocControl;
import com.midas.tsp.annotations.LogT;
import com.midas.tsp.annotations.LogTs;
import com.midas.tsp.controller.AbstractController;
import com.midas.tsp.exceptions.TSPException;
import com.midas.tsp.model.PropertiesTSP;
import com.midas.tsp.model.TeamMember;
import com.midas.tsp.model.dao.BasicDAO;
import com.midas.tsp.util.Utility;


/**
 * Controller TeamMembersController
 * @author Carlos Ivan Duarte C.
 * @date 28/03/2011
 */
@LogTs({@LogT(cycle=3, date="27/03/2011", id="28", time=120, who="CIDC")})
public class TeamMembersController extends AbstractController {
	private static final String TEAM_PATH = "/Volumes/Archivos/ECOS_NotSync/workspace/midas-tsp/properties/team.properties";
	//private static final String ROLES_PATH = "C:\\Users\\carlos.duarte\\workspace\\midas-tsp\\properties\\team.properties";
	private static final String ROLES_PATH = "/Volumes/Archivos/ECOS_NotSync/workspace/midas-tsp/properties/roles.properties";
	private BasicDAO dao;
	private List<TeamMember> teamMembers;
	
	/**
	 * Default Constructor, initialize DAO
	 * @throws TSPException
	 */
	public TeamMembersController() throws TSPException {
		dao = new BasicDAO(TEAM_PATH);
		teamMembers = findTeamMembers();
	}
	
	/**
	 * Find TeamMembers on Properties file
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	@SuppressWarnings("unchecked")
	public List<TeamMember> findTeamMembers() throws TSPException {
		return (List<TeamMember>) Utility.convertoToPropertiesTSP(TEAM_PATH);
	}
	
	/**
	 * Find One TeamMember from Id
	 * @param id
	 * @return
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 6, type = LocControl.LocType.NEW, who = "CIDC")})
	public TeamMember findTeamMember(Integer id) throws TSPException {
		for (TeamMember teamMember: teamMembers) {
			if (teamMember.getId().equals(id)) {
				return teamMember;
			}
		}
		return null;
	}
	
	/**
	 * Save the actual List of TeamMember
	 * @throws TSPException
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 2, type = LocControl.LocType.NEW, who = "CIDC")})
	public void save() throws TSPException {
		Properties prop = Utility.convertToProperties(teamMembers);
		dao.save(prop);
	}
	
	@SuppressWarnings("unchecked")
	public List<PropertiesTSP> findRoles() throws TSPException {
		return (List<PropertiesTSP>) Utility.convertoToPropertiesTSP(ROLES_PATH);
	}
	
	public PropertiesTSP findRol(Integer id) throws TSPException {
		for (PropertiesTSP rol: findRoles()) {
			if (rol.getId().equals(id.toString())) {
				return rol;
			}
		}
		return null;
	}
	
	/**
	 * getter TeamMembers
	 * @return
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	/**
	 * setter TeamMembers
	 * @param teamMember
	 */
	@LocControl(value = {
			@Loc(cycle = 3, size = 1, type = LocControl.LocType.NEW, who = "CIDC")})
	public void setTeamMembers(List<TeamMember> teamMember) {
		this.teamMembers = teamMember;
	}
}
