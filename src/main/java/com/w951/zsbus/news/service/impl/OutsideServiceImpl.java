package com.w951.zsbus.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.bean.BeanUtil;
import com.w951.zsbus.news.dto.OutsideDTO;
import com.w951.zsbus.news.entity.Outside;
import com.w951.zsbus.news.service.OutsideService;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-16<br>
 * 时间：15:49:37<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Component
public class OutsideServiceImpl implements OutsideService {
	@Resource
	private HibernateDao hibernateDao;
	
	private static final String QUERY_OUTSIDE_BY_PID = "FROM Outside t WHERE t.outsidePid = :outsidePid ORDER BY t.outsideSort";

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Outside entity) {
		entity = get(entity.getOutsideId());
		if (entity.getOutsidePid().equals("0")) {
			return "禁止操作系统目录";
		} else {
			// 递归删除所属目录
			List<Outside> list = hibernateDao.queryListByHql(QUERY_OUTSIDE_BY_PID, new String[][] { new String[] { "outsidePid", entity.getOutsideId() } });
			for (Outside outside : list) {
				deleteTree(outside);
				hibernateDao.delete(outside);
			}
			hibernateDao.delete(entity);

			return null;
		}
	}

	public Outside get(String id) {
		return hibernateDao.get(new Outside(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new Outside());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Outside entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<Outside> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new Outside(), order);
		} else {
			return hibernateDao.queryList(new Outside());
		}
	}

	public List<Outside> queryPageList(int pageIndex, int pageSize,
			String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new Outside(), pageIndex,
					pageSize, order);
		} else {
			return hibernateDao.queryPageList(new Outside(), pageIndex,
					pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(Outside entity) {
		if (entity.getOutsidePid().equals("0")) {
			return "禁止操作系统目录";
		} else {
			Outside outside = get(entity.getOutsideId());
			BeanUtil.beanToBean(entity, outside);
			hibernateDao.update(entity);
			return null;
		}
	}
	
	/*----------自定义接口----------*/

	@Override
	public List<OutsideDTO> queryTreeByPid(String pid) {
		List<OutsideDTO> dtos = new ArrayList<OutsideDTO>();
		List<Outside> list = hibernateDao.queryListByHql(QUERY_OUTSIDE_BY_PID, new String[][] { new String[] { "outsidePid", pid } });

		for (Outside outside : list) {
			OutsideDTO dto = new OutsideDTO();
			BeanUtil.beanToBean(dto, outside);
			dtos.add(dto);

			// 使用递归创建整棵树

			createTree(dto);
		}

		return dtos;
	}

	/**
	 * 递归查询
	 * 
	 * @param dto
	 */
	private void createTree(OutsideDTO dto) {
		List<Outside> subList = hibernateDao.queryListByHql(QUERY_OUTSIDE_BY_PID, new String[][] { new String[] { "outsidePid", dto.getOutsideId() } });
		if (subList != null && subList.size() > 0) {
			List<OutsideDTO> subDtos = new ArrayList<OutsideDTO>();
			// 递归中的循环是不会因为重新调用自身而不再循环，会一层一层往上继续循环
			for (Outside subOutside : subList) {
				OutsideDTO subDto = new OutsideDTO();
				BeanUtil.beanToBean(subDto, subOutside);
				subDtos.add(subDto);

				createTree(subDto);
			}
			dto.setChildren(subDtos);
		}
	}

	/**
	 * 递归删除
	 * 
	 * @param outside
	 */
	private void deleteTree(Outside outside) {
		List<Outside> subList = hibernateDao.queryListByHql(QUERY_OUTSIDE_BY_PID, new String[][] { new String[] { "outsidePid", outside.getOutsideId() } });
		if (subList != null && subList.size() > 0) {
			for (Outside subOutside : subList) {
				deleteTree(subOutside);
				hibernateDao.delete(subOutside);
			}
		}
	}

}